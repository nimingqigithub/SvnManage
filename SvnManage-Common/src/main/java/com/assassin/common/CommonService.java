package com.assassin.common;

import com.assassin.paginate.PaginateDataResponse;
import com.assassin.paginate.PaginateQueryProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
public class CommonService <D extends CommonDao<T>, T extends CommonEntity<T>> {
    @Autowired
    protected D dao  ;


    /**
     * 通过ID查找
     * 修改文件测试
     * @param id
     * @return
     */
    public T findById(String id){
        return dao.findById(id);
    }

    /**
     * 通过Class查找
     * @param entity
     * @return
     */
    public List<T> findByClass(T entity){
        return dao.findByClass(entity);
    }

    /**
     * 新增记录
     * @param entity
     * @return
     */
    public int insert(T entity){
        return dao.insert(entity);
    }

    /**
     * 更新记录
     * @param entity
     * @return
     */
    public int update(T entity){
        return dao.update(entity);
    }

    /**
     * 通过class 删除记录
     * @param limitEntity
     * @return
     */
    public int delete(T limitEntity){
        return dao.delete(limitEntity);
    }

    /**
     * 通过ID删除记录
     * @param id
     * @return
     */
    public int deleteById(String id){
        return dao.deleteById(id);
    }


    /**
     * 分页查询
     * @param entity
     * @return
     */
    public PaginateDataResponse listPage(T entity){
        List<T> entityList = dao.findByClassPaginate(entity);
        PaginateDataResponse response = new PaginateDataResponse();
        response.setStart(entity.getStart());
        response.setSize(entity.getSize());
        response.setSort(entity.getSort());
        response.setOrder(entity.getOrder());
        response.setTotal(dao.findByClassPaginateCount(entity));
        response.setData(entityList);
        return response;
    }


    /**
     * 根据条件查询全部
     * @param limitEntity
     * @return
     */
    public  List<T> listPageExcel(T limitEntity){
        List<T> limitEntityList = dao.findByClassAll(limitEntity);
        return limitEntityList;
    }
}
