package com.fabri.bazar.Service;

import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepo;


    @Override
    public List<Cliente> getCliente() {
        List <Cliente> clientes = clienteRepo.findAll();
        return clientes;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        Cliente cliente= clienteRepo.findById(id_cliente).orElse(null);
        return cliente;
    }

    @Override
    public void editCliente(Long id_cliente, String nuevoNombre, String nuevoApellido, String nuevoDni) {
        Cliente cliente = this.findCliente(id_cliente);

        cliente.setNombre(nuevoNombre);
        cliente.setApellido(nuevoApellido);
        cliente.setDni(nuevoDni);

        this.saveCliente(cliente);
    }
}
