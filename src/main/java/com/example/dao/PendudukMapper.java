package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KotaModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;

@Mapper
public interface PendudukMapper {
	@Select("select * from penduduk where nik = #{nik}")
    PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("select * from penduduk where id_keluarga = #{id_keluarga}")
	List<PendudukModel> selectAnggotaKeluarga(@Param("id_keluarga") int id_keluarga);
	
	@Insert("insert into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin," +
			"is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga," + 
			"golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}," +
			"#{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}," +
			"#{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
    void insertPenduduk (PendudukModel penduduk);

}
