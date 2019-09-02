package com.suixin.spring.anno.cp2.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 自定义扫描.通过获取到的类信息判断是否加入
 */
//先实现TypeFilter接口
public class AchengTypeFilter implements TypeFilter{

	private ClassMetadata classMetadata;

	/*
	 * MetadataReader:读取到当前正在扫描类的信息
	 * MetadataReaderFactory:可以获取到其他任何类信息
	 */

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//获取当前正在扫描的类信息
		classMetadata = metadataReader.getClassMetadata();
		//获取当前类资源(类的路径)
		Resource resource = metadataReader.getResource();

		String className = classMetadata.getClassName();
		System.out.println("----->"+className);

		//根据获取的数据,进行处理,返回boolean值
		if(className.contains("order")){//当类包含er字符, 则匹配成功,返回true
			return true;
		}
		return false;
	}

}
