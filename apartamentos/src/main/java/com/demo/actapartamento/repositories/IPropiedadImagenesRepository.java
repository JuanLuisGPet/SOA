package com.demo.actapartamento.repositories;
import com.demo.actapartamento.models.PropiedadImagenesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface IPropiedadImagenesRepository extends JpaRepository<PropiedadImagenesModel, Integer> {
            List<PropiedadImagenesModel> findByPropiedadIdPropiedad(Long propiedadId);
            List<PropiedadImagenesModel> findByPropiedadIdPropiedadOrderByOrdenAsc(Long propiedadId);
            Optional<PropiedadImagenesModel> findByPropiedadIdPropiedadAndEsPrincipal(Long propiedadId, Boolean esPrincipal);
}
