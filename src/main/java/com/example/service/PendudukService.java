package com.example.service;

import java.util.List;

import com.example.model.KotaModel;
import com.example.model.PendudukModel;

public interface PendudukService {

	PendudukModel selectPenduduk (String nik);
	
	List<PendudukModel> selectAnggotaKeluarga(int id_keluarga);
	
	void insertPenduduk (PendudukModel penduduk);
}
