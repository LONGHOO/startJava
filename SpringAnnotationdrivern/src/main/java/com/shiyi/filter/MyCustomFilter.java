package com.shiyi.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-04-24 21:56
 * @Descrption
 **/
public class MyCustomFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取被扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println(classMetadata);
        //获取被扫描类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        System.out.println(annotationMetadata);
        //获取被扫描类的信息（包括路径。。。）
        Resource resource = metadataReader.getResource();
        System.out.println(resource);
        return false;
    }
}
