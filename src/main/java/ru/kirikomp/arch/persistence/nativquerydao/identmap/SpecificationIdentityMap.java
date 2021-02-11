package ru.kirikomp.arch.persistence.nativquerydao.identmap;

import ru.kirikomp.arch.persistence.entity.Specification;
import ru.kirikomp.arch.persistence.nativquerydao.SpecificationRepository;
import ru.kirikomp.arch.persistence.nativquerydao.objectmapper.SpecificationMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SpecificationIdentityMap implements SpecificationRepository {

    private final SpecificationRepository specificationMapper = new SpecificationMapper();
    private static Map specificationMap = new HashMap();

    private static void addSpecification(Specification specification) {
        specificationMap.put(specification.getId(), specification);
    }

    private static Specification getSpecification(UUID key) {
        return (Specification) specificationMap.get(key);
    }

    private static void deleteSpecification(UUID key) {
        specificationMap.remove(key);
    }

    @Override
    public Specification findById(UUID id) throws SQLException {
        Specification specification = null;
        if (specificationMap.containsKey(id)) {
            specification = getSpecification(id);
        } else {
            specification = specificationMapper.findById(id);
            addSpecification(specification);
        }
        return specification;
    }

    @Override
    public List<Specification> findAll() throws SQLException {
        List<Specification> all = specificationMapper.findAll();
        all.forEach(SpecificationIdentityMap::addSpecification);
        return all;
    }

    @Override
    public Specification insert(Specification specification) throws SQLException {
        Specification insert = specificationMapper.insert(specification);
        addSpecification(insert);
        return insert;
    }

    @Override
    public void deleteById(UUID id) throws SQLException {
        specificationMapper.deleteById(id);
        deleteSpecification(id);
    }
}
