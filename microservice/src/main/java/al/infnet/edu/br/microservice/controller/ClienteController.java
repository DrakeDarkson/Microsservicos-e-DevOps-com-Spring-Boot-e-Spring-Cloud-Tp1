package al.infnet.edu.br.microservice.controller;

import al.infnet.edu.br.microservice.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCounter++);
        clientes.add(cliente);
        return cliente;
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existingCliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingCliente != null) {
            existingCliente.setNome(cliente.getNome());
            existingCliente.setEmail(cliente.getEmail());
        }

        return existingCliente;
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clientes.removeIf(cliente -> cliente.getId().equals(id));
    }
}