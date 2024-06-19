package info.heitor.at_empresa.service;

import info.heitor.at_empresa.model.Departamento;
import info.heitor.at_empresa.model.Funcionario;
import info.heitor.at_empresa.repository.DepartamentoRepository;
import info.heitor.at_empresa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Departamento> getAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> getById(Long id) {
        return departamentoRepository.findById(id);
    }

    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public void delete(Long id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        if (departamento.isPresent()) {
            List<Funcionario> funcionarios = departamento.get().getFuncionarios();
            for (Funcionario funcionario : funcionarios) {
                funcionario.setDepartamento(null);
                funcionarioRepository.save(funcionario);
            }
            departamentoRepository.deleteById(id);
        }
    }

    public Departamento update(Long id, Departamento updatedDepartamento) {
        return departamentoRepository.findById(id)
                .map(departamento -> {
                    departamento.setNome(updatedDepartamento.getNome());
                    departamento.setLocal(updatedDepartamento.getLocal());
                    return departamentoRepository.save(departamento);
                })
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
    }

    public Funcionario addFuncionarioToDepartamento(Long funcionarioId, Long departamentoId) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(funcionarioId);
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(departamentoId);

        if (funcionarioOpt.isPresent() && departamentoOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            Departamento departamento = departamentoOpt.get();
            funcionario.setDepartamento(departamento);
            return funcionarioRepository.save(funcionario);
        } else {
            throw new RuntimeException("Funcionário ou Departamento não encontrado");
        }
    }

}
