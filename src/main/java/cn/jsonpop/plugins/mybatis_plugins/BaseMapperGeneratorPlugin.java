package cn.jsonpop.plugins.mybatis_plugins;

/**
 * description 生成Mapper类继承BaseMapper
 * 创建时间 2018/10/19
 *
 * @author 仇兴洲
 */

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;

import java.lang.reflect.Field;
import java.util.*;

public class BaseMapperGeneratorPlugin extends PluginAdapter {
    private String baseMapperPackage;
    public boolean validate(final List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        baseMapperPackage = properties.getProperty("baseMapperPackage");
    }

    public boolean clientGenerated(final Interface interfaze, final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        final FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<" + introspectedTable.getBaseRecordType() + ">");
        final FullyQualifiedJavaType imp = new FullyQualifiedJavaType(baseMapperPackage);
        interfaze.addSuperInterface(fqjt);
        final Class<? extends Interface> ifclazz = interfaze.getClass();
        try {
            final Field importedTypes = ifclazz.getDeclaredField("importedTypes");
            importedTypes.setAccessible(true);
            final Set setValue = (Set) importedTypes.get(interfaze);
            setValue.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        interfaze.addImportedType(imp);
        interfaze.getMethods().clear();
        interfaze.getAnnotations().clear();
        interfaze.addAnnotation("@Repository");
        return true;
    }
}
