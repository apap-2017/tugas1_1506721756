package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class PendudukController {

	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
    PendudukService pendudukDAO;
	@Autowired
	KelurahanService kelurahanDAO;
	@Autowired
	KecamatanService kecamatanDAO;
	@Autowired
	KotaService kotaDAO;
	
	@RequestMapping("/")
    public String index ()
    {
        return "index";
    }
	
	@RequestMapping("/penduduk/tambah")
    public String tambahPenduduk ()
    {
        return "tambah-penduduk";
    }
	
	@RequestMapping (value = "/penduduk/tambah" , method = RequestMethod.POST)
	public String tambahPenduduk (Model model,
  		@RequestParam ( value = "nama" , required = true ) String nama ,
  		@RequestParam ( value = "tempat_lahir" , required = true ) String tempat_lahir ,
  		@RequestParam ( value = "tanggal_lahir" , required = true ) String tanggal_lahir,
		@RequestParam ( value = "jenis_kelamin" , required = true ) int jenis_kelamin,
		@RequestParam ( value = "is_wni" , required = true ) int is_wni,
		@RequestParam ( value = "id_keluarga" , required = true ) int id_keluarga,
  		@RequestParam ( value = "agama" , required = true ) String agama,
  		@RequestParam ( value = "pekerjaan" , required = true ) String pekerjaan,
  		@RequestParam ( value = "status_perkawinan" , required = true ) String status_perkawinan,
  		@RequestParam ( value = "status_dalam_keluarga" , required = true ) String status_dalam_keluarga,
  		@RequestParam ( value = "golongan_darah" , required = true ) String golongan_darah,
  		@RequestParam ( value = "is_wafat" , required = true ) int is_wafat
	) throws Exception
    {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(id_keluarga);
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
		String nik = generateNik(kelurahan.getKode_kelurahan(), tanggal_lahir, jenis_kelamin);
		model.addAttribute("nik",nik);
		pendudukDAO.insertPenduduk(new PendudukModel(0, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat));
		return "sukses-tambah-penduduk";
    }
	
	private String generateNik(String id_kelurahan, String tanggal_lahir, int jenis_kelamin) throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal_lahir);
		String day = new SimpleDateFormat("dd").format(date);
		String month = new SimpleDateFormat("MM").format(date);
		String year = new SimpleDateFormat("YY").format(date);

		day = jenis_kelamin ==1 ? "" + (Integer.parseInt(day) + 40) : day;
		String nik = id_kelurahan.substring(0, 6) + day + month + year + "0001"; 
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik); 
		while(penduduk != null) {
			nik = "" + (Long.parseLong(nik) + 1);
			penduduk = pendudukDAO.selectPenduduk(nik);
		}
		return nik;
	}
	
	@RequestMapping("/keluarga/tambah")
    public String tambahKeluarga (Model model)
    {
		List<KotaModel> cities = kotaDAO.selectAllKota();
        model.addAttribute("cities", cities);
        List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan();
        model.addAttribute("kecamatans", kecamatans);
        List<KelurahanModel> kelurahans = kelurahanDAO.selectAllKelurahan();
        model.addAttribute("kelurahans", kelurahans);
		return "tambah-keluarga";
    }
	
	@RequestMapping (value = "/keluarga/tambah" , method = RequestMethod.POST)
	public String tambahKeluarga (Model model,
  		@RequestParam ( value = "alamat" , required = true ) String alamat ,
  		@RequestParam ( value = "rt" , required = true ) String rt ,
  		@RequestParam ( value = "rw" , required = true ) String rw,
		@RequestParam ( value = "id_kelurahan" , required = true ) int id_kelurahan
	) throws Exception
    {
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(id_kelurahan);
		String nkk = generateNkk(kelurahan.getKode_kelurahan());
		model.addAttribute("nkk",nkk);
		//keluargaDAO.insertKeluarga(new KeluargaModel(0, nkk, alamat, rt, rw, id_kelurahan, 0));
		return "sukses-tambah-keluarga";
    }
	
	private String generateNkk(String id_kelurahan) throws Exception {
		Date date = new Date();
		String day = (new SimpleDateFormat("dd").format(date));
		String month = new SimpleDateFormat("MM").format(date);
		String year = new SimpleDateFormat("YY").format(date);
		
		String nkk = id_kelurahan.substring(0, 6) + day + month + year + "0001"; 
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNkk(nkk); 
		while(keluarga != null) {
			nkk = "" + (Long.parseLong(nkk) + 1);
			keluarga = keluargaDAO.selectKeluargaByNkk(nkk);
		}
		return nkk;
	}

	
	@RequestMapping("/penduduk")
	public String penduduk (@RequestParam(value = "nik") String nik, Model model)
	{
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(penduduk.getTanggal_lahir());
			penduduk.setTanggal_lahir(new SimpleDateFormat("dd MMMM yyyy").format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
		
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		
		String status_mati = penduduk.getIs_wafat() == 0 ? "hidup" : "mati";
		String is_wni = penduduk.getIs_wni() == 0 ? "Bukan WNI" : "WNI";
		String jenis_kelamin = penduduk.getJenis_kelamin() == 0 ? "pria" : "wanita";
		
		model.addAttribute("status_mati", status_mati);
		model.addAttribute("is_wni", is_wni);
		model.addAttribute("jenis_kelamin", jenis_kelamin);
		return "penduduk";
	}
	
	@RequestMapping("/keluarga")
	public String keluarga (@RequestParam(value = "nkk") String nkk, Model model) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNkk(nkk);
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
		
		List<PendudukModel> anggotaKeluarga = pendudukDAO.selectAnggotaKeluarga(keluarga.getId());
		for(PendudukModel penduduk : anggotaKeluarga) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(penduduk.getTanggal_lahir());
				penduduk.setTanggal_lahir(new SimpleDateFormat("dd-MM-yyyy").format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		model.addAttribute("anggotaKeluarga", anggotaKeluarga);
		
		return "keluarga";
	}
}
