package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.dao.KelurahanMapper;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;

@Service
public class KecamatanServiceDatabase implements KecamatanService {

	@Autowired
	private KecamatanMapper kecamatanMapper;
	    
	@Override
	public KecamatanModel selectKecamatan(int id_kecamatan) {
		return kecamatanMapper.selectKecamatan(id_kecamatan);
	}

	@Override
	public List<KecamatanModel> selectAllKecamatan() {
		return kecamatanMapper.selectAllKecamatan();
	}
}
	