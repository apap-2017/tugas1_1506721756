package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("select * from kelurahan where id = #{id_kelurahan}")
	KelurahanModel selectKelurahan(@Param("id_kelurahan") int id_kelurahan);
	
	@Select("select * from kelurahan")
	List<KelurahanModel> selectAllKelurahan();
}
