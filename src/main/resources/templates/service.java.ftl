package ${package.Service};

<#assign voCls = "${table.entityName?replace('DO','VO')}">
<#assign comIns = "${table.entityPath?replace('DO','')}">
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
    * ${table.comment!} 服务类
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>