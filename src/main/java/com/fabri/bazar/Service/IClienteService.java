package com.fabri.bazar.Service;

import com.fabri.bazar.Model.Cliente;
import java.util.List;

public interface IClienteService {

    public List<Cliente> getCliente();

    public void saveCliente(Cliente cliente);

    public void deleteCliente(Long id_cliente);

    public Cliente findCliente(Long id_cliente);

    public void editCliente(Long id_cliente, String nuevoNombre, String nuevaApellido, String nuevoDni);


}
