package com.example.service;

import com.example.model.KeluargaModel;


public interface KeluargaService {
	KeluargaModel selectKeluarga(int id_keluarga);
	
	KeluargaModel selectKeluargaByNkk(String nkk);
	
	void insertKeluarga(KeluargaModel keluarga);
}
