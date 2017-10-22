package com.example.service;
import java.util.List;

import com.example.model.KecamatanModel;

public interface KecamatanService {
	KecamatanModel selectKecamatan(int id_kecamatan);
	
	List<KecamatanModel> selectAllKecamatan();
}
