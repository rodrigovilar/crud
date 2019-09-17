package com.example.demo.core.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.core.BusinessException;
import com.example.demo.core.Model;
import com.example.demo.core.repository.CrudBaseRepository;


/**
 * The 'CrudService' class provides the common API for all CRUD services.
 * <p>
 * All CRUD services MUST extend this class.
 * <p>
 * Provides validations, insertion, update and deletion.
 *
 * @param <M> Model type.
 * @param <T> ID type.
 * @author Virtus
 */
public abstract class CrudService<M extends Model<T>, T extends Serializable> extends SearchService<M, T> {

    /**
     * Gets the model CRUD Repository.
     *
     * @return CRUD Repository.
     */
    protected abstract CrudBaseRepository<M, T> getRepository();

    /**
     * Execution before the insert operation.
     *
     * @param model Model.
     */
    public M preInsert(M model) throws BusinessException {
        return model;
    }
    
    /**
     * Validates the insertion before perform.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateInsert(M model) throws BusinessException {

    }

    /**
     * Inserts the model.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public M insert(M model) throws BusinessException {
        model = preInsert(model);

        validateInsert(model);

        return getRepository().save(model);
    }
    
    /**
     * Execution before the update operation.
     *
     * @param model Model.
     */
    public M preUpdate(M model) throws BusinessException {
        return model;
    }

    /**
     * Validates the update before perform.
     *
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateUpdate(M model) throws BusinessException {

    }

    /**
     * Updates the model.
     *
     * @param id    ID.
     * @param model Model.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public void update(T id, M model) throws BusinessException {
        model = preUpdate(model);

        validateUpdate(model);

        getRepository().save(model);
    }

    /**
     * Execution before the delete operation.
     *
     * @param id ID.
     */
    public void preDelete(T id) throws BusinessException {
    	
    }
    
    /**
     * Validates the deletion before perform.
     *
     * @param id ID.
     * @throws BusinessException If some rule is not acceptable.
     */
    public void validateDelete(T id) throws BusinessException {

    }

    /**
     * Deletes the model.
     *
     * @param id ID.
     * @throws BusinessException If some rule is not acceptable.
     */
    @Transactional
    public void delete(T id) throws BusinessException {
        validateDelete(id);

        preDelete(id);
        
        getRepository().deleteById(id);
    }
    
    /**
     * Deletes all IDs. 
     * 
     * @param ids
     * 		IDs.
     * @throws BusinessException 
     * 		If some rule is not acceptable.
     */
    public void delete(List<T> ids) throws BusinessException {
    	for (T id : ids) {
    		this.delete(id);
    	}
    }
}