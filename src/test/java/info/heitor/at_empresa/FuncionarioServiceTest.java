package info.heitor.at_empresa;

import info.heitor.at_empresa.model.Funcionario;
import info.heitor.at_empresa.repository.FuncionarioRepository;
import info.heitor.at_empresa.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FuncionarioServiceTest {

    @Autowired
    private FuncionarioService funcionarioService;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void testGetAllFuncionarios() {
        Funcionario func1 = new Funcionario(1L, "Funcionario 1", "Endereco 1", "123456", "funcionario1@example.com", Date.from(LocalDate.of(1989,11,9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Funcionario func2 = new Funcionario(2L, "Funcionario 2", "Endereco 2", "654321", "funcionario2@example.com", Date.from(LocalDate.of(1999,4,21).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        List<Funcionario> funcionarios = Arrays.asList(func1, func2);

        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<Funcionario> result = funcionarioService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetFuncionarioById() {
        Funcionario func = new Funcionario(1L, "Funcionario Teste", "Endereco Teste", "987654", "teste@example.com", Date.from(LocalDate.of(2003,7,11).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(func));

        Optional<Funcionario> result = funcionarioService.getById(1L);
        assertEquals("Funcionario Teste", result.get().getNome());
    }

    @Test
    public void testCreateFuncionario() {
        Funcionario func = new Funcionario(1L, "Funcionario Teste", "Endereco Teste", "123456", "teste@example.com", Date.from(LocalDate.of(2003,7,11).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        when(funcionarioRepository.save(func)).thenReturn(func);

        Funcionario result = funcionarioService.create(func);
        assertEquals("Funcionario Teste", result.getNome());
    }

    @Test
    public void testDeleteFuncionario() {
        Long funcionarioId = 1L;

        when(funcionarioRepository.existsById(funcionarioId)).thenReturn(true);

        boolean result = funcionarioService.delete(funcionarioId);
        assertTrue(result);

        verify(funcionarioRepository, times(1)).deleteById(funcionarioId);
    }

    @Test
    public void testUpdateFuncionario() {
        Long funcionarioId = 1L;
        Funcionario funcExistente = new Funcionario(funcionarioId, "Funcionario Antigo", "Endereco Antigo", "123456", "old@example.com", Date.from(LocalDate.of(2003,7,11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Funcionario funcAtualizado = new Funcionario(funcionarioId, "Novo Funcionario", "Novo Endereco", "999999", "new@example.com", Date.from(LocalDate.of(2003,7,11).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcExistente));

        when(funcionarioRepository.save(any(Funcionario.class))).thenAnswer(invocation -> {
            Funcionario funcSalvo = invocation.getArgument(0);
            return funcSalvo;
        });

        Optional<Funcionario> result = funcionarioService.update(funcionarioId, funcAtualizado);

        assertEquals("Novo Funcionario", result.orElseThrow().getNome());
    }
}
