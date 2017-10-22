package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KeluargaModel;

@Mapper
public interface KeluargaMapper {
	@Select("select * from keluarga where id = #{id_keluarga}")
	KeluargaModel selectKeluarga(@Param("id_keluarga") int id_keluarga);

	@Select("select * from keluarga where nomor_kk = #{nkk}")
	KeluargaModel selectKeluargaByNkk(@Param("nkk") String nkk);
	
	@Insert("insert into keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) "
			+ "VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, #{is_tidak_berlaku})")
	void insertKeluarga (KeluargaModel keluarga);
	
}
