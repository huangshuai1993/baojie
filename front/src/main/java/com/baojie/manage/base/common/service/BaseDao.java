package com.baojie.manage.base.common.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 单表通用的service层的抽象类
 * 	目前封装的方法不全，可以在此抽象类中自行封装方法
 */
public abstract class BaseDao<T extends BaseDO> {

    public final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private Mapper<T> mapper;
    
    /**
     * 根据id查询数据
     * 
     * @param id
     * @return
     */
    public T queryById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     * 
     * @return
     */
    public List<T> queryAll() {
        return this.mapper.select(null);
    }

    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     * 
     * @param record
     * @return
     */
    public T queryOne(T record) {
        return this.mapper.selectOne(record);
    }

    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     * 
     * @param record
     * @return
     */
    public T queryOne(Example example) {
        List<T> list = this.mapper.selectByExample(example);
        if (list == null || list.size() == 0) {
        	return null;
        }
        return list.get(0);
    }

    /**
     * 根据条件查询数据列表
     * 
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record) {
        return this.mapper.select(record);
    }

    /**
     * 根据条件查询数据列表
     * 
     * @param example
     * @return
     */
    public List<T> queryListByExample(Example example) {
        return this.mapper.selectByExample(example);
    }

    /**
     * 分页查询
     * 
     * @param page
     * @param rows
     * @param record
     * @return
     */
    public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record) {
        // 设置分页条件
        PageHelper.startPage(page, rows);
        //添加默认按照创建时间排序
        PageHelper.orderBy("data_create_time desc");
        List<T> list = this.queryListByWhere(record);
        return new PageInfo<T>(list);
    }
    
    
    public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record, boolean desc) {
        // 设置分页条件
        PageHelper.startPage(page, rows);
        if(desc) {
            PageHelper.orderBy("data_id desc");
        }
        List<T> list = this.queryListByWhere(record);
        return new PageInfo<T>(list);
    }
    
    
    public PageInfo<T> queryPageListByExample(Integer page, Integer rows, Example example, boolean desc) {
        // 设置分页条件
        PageHelper.startPage(page, rows);
        if(desc) {
        	PageHelper.orderBy("data_id desc");
        }
        List<T> list = mapper.selectByExample(example);
        return new PageInfo<T>(list);
    }
    

    /**
     * 根据code， 查询唯一一条记录数据
     * 
     * @param clazz
     * @param property：
     *            业务主键字段code
     * @param value：
     *            code内容
     * @return
     */
    public T queryOneByCode(Class<T> clazz, String property, String value) {
        Example example = new Example(clazz);
        example.createCriteria().andEqualTo(property, value);
        List<T> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() > 1) {
            logger.error("存在code相同的数据");
            throw new RuntimeException(clazz + "，只要一条记录，结果查询出" + list.size() + "条记录");
        }
        return list.get(0);
    }

    /**
     * 根据字段(等号关系)，查询数量
     * 
     * @param record
     * @return
     */
    public Integer queryCount(T record) {
        return this.mapper.selectCount(record);
    }

    /**
     * 根据字段(等号关系)，查询数量
     * 
     * @param example
     * @return
     */
    public Integer queryCountByExample(Example example) {
        return this.mapper.selectCountByExample(example);
    }

    /**
     * 新增数据，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        record.setDataFlag(1); // 1表示有效数据
        return this.mapper.insert(record);
    }

    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer saveSelective(T record) {
    	 record.setCreated(new Date());
         record.setUpdated(record.getCreated());
         record.setDataFlag(1); // 1表示有效数据
        return this.mapper.insertSelective(record);
    }

    /**
     * 根据主键id，修改数据，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer update(T record) {
        record.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键id，修改数据，使用不为null的字段，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer updateSelective(T record) {
        record.setUpdated(new Date());
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键id，修改数据，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer updateByExample(T record, Example example) {
        record.setUpdated(new Date());
        return this.mapper.updateByExample(record, example);
    }

    /**
     * 根据主键id，修改数据，使用不为null的字段，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer updateByExampleSelective(T record, Example example) {
        record.setUpdated(new Date());
        return this.mapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据code, 更新数据
     * 
     * @param record
     *            需要更新的字段
     * @param clazz
     * @param code
     *            业务主键
     * @param value
     *            主键值
     * @return
     */
    public Integer updateByCode(T record, Class<T> clazz, String code, String value) {
        if (value == null) {
            throw new RuntimeException("updateByCode value不能为null");
        }
        record.setUpdated(new Date());
        // 组装条件
        Example example = new Example(clazz);
        example.createCriteria().andEqualTo(code, value);
        return this.mapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据id，删除数据
     * 
     * @param id
     * @return
     */
    public Integer deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ids，批量删除数据
     * 
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
        if (values == null) {
            throw new RuntimeException("updateByCode value不能为null");
        }
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件，删除数据
     * 
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record) {
        return this.mapper.delete(record);
    }

}
