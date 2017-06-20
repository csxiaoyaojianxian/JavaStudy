package com.csxiaoyao.springmvc.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.base.Preconditions;

import com.csxiaoyao.springmvc.dao.DataOperator;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> implements DataOperator<T> {

	protected Class<T> entityType;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected final void setEntityType(final Class<T> entityType) {
		this.entityType = Preconditions.checkNotNull(entityType);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query queryObject = getCurrentSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; ++i) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	@Override
	public final T findOne(final int id) {
		return (T) getCurrentSession().get(entityType, id);
	}

	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + entityType.getName()).list();
	}

	@Override
	public final void create(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public final void update(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().update(entity);
	}

	@Override
	public final void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final int entityId) {
		final T entity = findOne(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	@Override
	public final List<T> findByHql(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

}
