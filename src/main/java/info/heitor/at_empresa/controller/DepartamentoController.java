package info.heitor.at_empresa.controller;

import info.heitor.at_empresa.model.Departamento;
import info.heitor.at_empresa.model.Funcionario;
import info.heitor.at_empresa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        Optional<Departamento> departamento = departamentoService.getById(id);
        return departamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.create(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento updatedDepartamento) {
        try {
            Departamento departamento = departamentoService.update(id, updatedDepartamento);
            return ResponseEntity.ok(departamento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{departamentoId}/funcionario/{funcionarioId}")
    public ResponseEntity<Funcionario> addFuncionarioToDepartamento(
            @PathVariable Long funcionarioId,
            @PathVariable Long departamentoId) {
        try {
            Funcionario funcionario = departamentoService.addFuncionarioToDepartamento(funcionarioId, departamentoId);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
