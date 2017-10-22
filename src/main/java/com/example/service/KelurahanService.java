package com.example.service;

import java.util.List;

import com.example.model.KelurahanModel;

public interface KelurahanService {
	KelurahanModel selectKelurahan(int id_kelurahan);
	List<KelurahanModel> selectAllKelurahan();
}
