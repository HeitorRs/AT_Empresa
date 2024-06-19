package info.heitor.at_empresa.service;

import info.heitor.at_empresa.model.Funcionario;
import info.heitor.at_empresa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario create(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> update(Long id, Funcionario updatedFuncionario) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(updatedFuncionario.getNome());
                    funcionario.setEndereco(updatedFuncionario.getEndereco());
                    funcionario.setTelefone(updatedFuncionario.getTelefone());
                    funcionario.setEmail(updatedFuncionario.getEmail());
                    funcionario.setDataNascimento(updatedFuncionario.getDataNascimento());
                    funcionario.setDepartamento(updatedFuncionario.getDepartamento());
                    return funcionarioRepository.save(funcionario);
                });
    }

    public boolean delete(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
