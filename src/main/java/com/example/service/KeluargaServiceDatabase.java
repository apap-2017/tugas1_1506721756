package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

@Service
public class KeluargaServiceDatabase implements KeluargaService {
	 @Autowired
	 private KeluargaMapper keluargaMapper;
	 
	 @Override
	public KeluargaModel selectKeluarga(int id_keluarga) {
		return keluargaMapper.selectKeluarga(id_keluarga);
	}

	@Override
	public KeluargaModel selectKeluargaByNkk(String nkk) {
		return keluargaMapper.selectKeluargaByNkk(nkk);
	}

	@Override
	public void insertKeluarga(KeluargaModel keluarga) {
		keluargaMapper.insertKeluarga(keluarga);
		
	}
}
