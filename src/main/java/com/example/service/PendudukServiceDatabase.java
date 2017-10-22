package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;

    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        return pendudukMapper.selectPenduduk (nik);
    }

	@Override
	public List<PendudukModel> selectAnggotaKeluarga(int id_keluarga) {
		
		return pendudukMapper.selectAnggotaKeluarga(id_keluarga);
	}

	@Override
	public void insertPenduduk(PendudukModel penduduk) {
		pendudukMapper.insertPenduduk(penduduk);
	}


}
