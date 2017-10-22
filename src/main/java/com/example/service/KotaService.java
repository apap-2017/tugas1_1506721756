package com.example.service;

import java.util.List;

import com.example.model.KotaModel;

public interface KotaService {
	KotaModel selectKota(int id_kota);
	
	List<KotaModel> selectAllKota();
}
