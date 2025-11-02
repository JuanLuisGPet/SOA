package com.demo.actapartamento.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.actapartamento.enums.TipoPropiedad;
import com.demo.actapartamento.models.PropiedadModel;
@Repository
public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {
    List<PropiedadModel> findByTipo(TipoPropiedad tipo);
    List<PropiedadModel> findByPropietarioId(Long propietarioId);
}
