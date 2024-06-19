package info.heitor.at_empresa;

import info.heitor.at_empresa.model.Departamento;
import info.heitor.at_empresa.model.Funcionario;
import info.heitor.at_empresa.repository.DepartamentoRepository;
import info.heitor.at_empresa.repository.FuncionarioRepository;
import info.heitor.at_empresa.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DepartamentoServiceTest {

    @Autowired
    private DepartamentoService departamentoService;

    @MockBean
    private DepartamentoRepository departamentoRepository;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void testCreateDepartamento() {
        Departamento dep = new Departamento(1L, "TI", "Prédio A", null);

        when(departamentoRepository.save(dep)).thenReturn(dep);

        Departamento result = departamentoService.create(dep);
        assertEquals("TI", result.getNome());
    }

    @Test
    public void testDeleteDepartamento() {
        Long departamentoId = 1L;

        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario funcionario1 = new Funcionario(1L, "Funcionario 1", "Endereço 1", "123456", "func1@example.com", null);
        Funcionario funcionario2 = new Funcionario(2L, "Funcionario 2", "Endereço 2", "654321", "func2@example.com", null);
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);

        Departamento departamento = new Departamento(departamentoId, "Departamento Teste", "Local Teste", funcionarios);

        when(departamentoRepository.findById(departamentoId)).thenReturn(Optional.of(departamento));

        departamentoService.delete(departamentoId);

        verify(funcionarioRepository, times(2)).save(any(Funcionario.class));

        verify(departamentoRepository, times(1)).deleteById(departamentoId);
    }

    @Test
    public void testUpdateDepartamento() {
        Long departamentoId = 1L;
        Departamento dep = new Departamento(departamentoId, "Novo Departamento", "Novo Local", null);

        when(departamentoRepository.findById(departamentoId)).thenReturn(Optional.of(new Departamento()));
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(dep);

        Departamento result = departamentoService.update(departamentoId, dep);
        assertEquals("Novo Departamento", result.getNome());
    }
}
