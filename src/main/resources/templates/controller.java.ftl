package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.java.Log;
import com.sys.comm.response.CommonReturnType;
import com.sys.controller.convert.*;
import com.sys.controller.vo.*;
import com.sys.entity.*;
import com.sys.service.*;
import java.util.*;

/*
${controllerMappingHyphen}
${table.entityPath}
${table.controllerName}
${table.entityPath}
*/
<#assign voCls = "${table.entityName?replace('DO','VO')}">
<#assign comIns = "${table.entityPath?replace('DO','')}">
<#assign voConvertCls = "${table.entityName?replace('DO','Convert')}">


/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Log
@Api(value = "${table.comment!}", description="${table.comment!}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-do","")}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName}  extends BaseController{
</#if>
    @Autowired
    private ${table.serviceName} ${comIns}Service;

    /**
     * 保存
     */
    @ApiOperation(value = "保存", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = CommonReturnType.class)
    @PostMapping(value="/save")
    public CommonReturnType<Object> add(@RequestBody ${voCls} inputVO) {
        CommonReturnType<Object> ret = null;

        this.${comIns}Service.saveOrUpdate(inputVO);

        ret = CommonReturnType.createSuccess(null);
        return ret;
    }


    /**
     * 详情
     */
/*
    @ApiOperation(value = "详情", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = CommonReturnType.class)
    @PostMapping(value="/view/{id}")
    public CommonReturnType<${voCls}> view(@PathVariable Long id) {
        CommonReturnType<${voCls}> ret = null;

        final ${entity} doObj = this.${comIns}Service.getById(id);
        final ${voCls} voObj = ${voConvertCls}.from(doObj);

        ret = CommonReturnType.createSuccess(voObj);

        return ret;
    }*/

    /**
     * 分页列表
     */
    /*
    @ApiOperation(value = "分页列表", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = CommonReturnType.class)
    @PostMapping(value="/list")
    public CommonReturnType<${voCls}> list(@RequestBody ${voCls} inputVO) {
        CommonReturnType<${voCls}> ret = null;
        final ${voCls} demoVO = new ${voCls}();
        ret = CommonReturnType.createSuccess(demoVO);
        return ret;
    }*/


    /**
     * 查询列表
     */
    @ApiOperation(value = "查询列表", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = CommonReturnType.class)
    @PostMapping(value="/list")
    public CommonReturnType<List<${voCls}>> list(@RequestBody RecordReqVO inputVO) {
        CommonReturnType<List<${voCls}>> ret = null;

        final List<${entity}> doList = this.${comIns}Service.list();
        List<${voCls}> list = ${voConvertCls}.from(doList);

        ret = CommonReturnType.createSuccess(list);

        return ret;
    }

}
</#if>
