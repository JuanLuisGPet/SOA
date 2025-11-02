package com.demo.actapartamento.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.enums.EstadoPago;
import com.demo.actapartamento.enums.MetodoPago;
import com.demo.actapartamento.models.PagosModel;
import com.demo.actapartamento.repositories.IPagosRepository;
@Service
public class PagosService {
    @Autowired
    private IPagosRepository pagosRepository;
    public List<PagosModel> getAllPagos() {
        return pagosRepository.findAll();
    }
    public Optional<PagosModel> getPagoById(Long id) {
        return pagosRepository.findById(id);
    }
    public PagosModel savePago(PagosModel pago) {
        return pagosRepository.save(pago);
    }
    public void deletePago(Long id) {
        pagosRepository.deleteById(id);
    }
    public Optional<PagosModel> updatePago(Long id, PagosModel pagoDetails) {
        return pagosRepository.findById(id)
                        .map(pagoToUpdate -> {
                pagoToUpdate.setReserva(pagoDetails.getReserva());
                pagoToUpdate.setMonto(pagoDetails.getMonto());
                pagoToUpdate.setMetodoPago(pagoDetails.getMetodoPago());
                pagoToUpdate.setEstadoPago(pagoDetails.getEstadoPago());
                pagoToUpdate.setFechaPago(pagoDetails.getFechaPago());
                pagoToUpdate.setReferenciaPago(pagoDetails.getReferenciaPago());
                pagoToUpdate.setDetallesPago(pagoDetails.getDetallesPago());
                return pagosRepository.save(pagoToUpdate);
            });
    }
    public List<PagosModel> getPagosByReserva(Long reservaId) {
        return pagosRepository.findByReservaId(reservaId);
    }
    public List<PagosModel> getPagosByEstado(EstadoPago estadoPago) {
        return pagosRepository.findByEstadoPago(estadoPago);
    }
    public List<PagosModel> getPagosByMetodoPago(MetodoPago metodoPago) {
        return pagosRepository.findByMetodoPago(metodoPago);
    }
    public List<PagosModel> getPagosByReservaAndEstado(Long reservaId, EstadoPago estadoPago) {
        return pagosRepository.findByReservaIdAndEstadoPago(reservaId, estadoPago);
    }
}
