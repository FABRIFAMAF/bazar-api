package com.fabri.bazar.Controller;

import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteServ;

    @GetMapping("/clientes/traer")
    public List<Cliente> getClientes(){
        return clienteServ.getCliente();
    }

    @PostMapping("/cliente/crear")
    public String saveCliente(Cliente cliente){
        clienteServ.saveCliente(cliente);
        return "El nuevo cliente fue creado correctamente";
    }

    @DeleteMapping("/cliente/borrar/{id}")
    public String deleteCliente(@PathVariable Long id_cliente){
        clienteServ.deleteCliente(id_cliente);
        return "El cliente fue eliminado correctamente";
    }

    @PutMapping("/cliente/editar/{id}")
    public Cliente editCliente(@PathVariable Long id_cliente,
                                 @RequestParam(required=false, name="nombre") String nuevoNombre,
                                 @RequestParam (required=false, name="apellido") String nuevoApellido,
                                 @RequestParam (required=false, name="dni") String nuevoDni) {
        clienteServ.editCliente(id_cliente, nuevoNombre, nuevoApellido, nuevoDni);

        Cliente cliente = clienteServ.findCliente(id_cliente);

        return cliente;

    }
}
