package com.assassin.common;

import com.assassin.paginate.PaginateQueryProperty;

/**
 * Created by Administrator on 2017/1/20.
 */
public abstract class CommonEntity<T> extends PaginateQueryProperty<T> implements Cloneable {

    @Override
    public T clone() throws CloneNotSupportedException {
        T newBody =  (T) super.clone();
        return newBody;
    }
}
