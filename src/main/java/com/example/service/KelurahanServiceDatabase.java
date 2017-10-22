package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

@Service
public class KelurahanServiceDatabase implements KelurahanService {

	@Autowired
	private KelurahanMapper kelurahanMapper;
	    
	@Override
	public KelurahanModel selectKelurahan(int id_kelurahan) {
		return kelurahanMapper.selectKelurahan(id_kelurahan);
	}

	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		return kelurahanMapper.selectAllKelurahan();
	}

}
