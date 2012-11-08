package br.siae.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.service.AbstractService;
import br.siae.dominio.academico.Nivel;


@Service
@Transactional
public class NivelService extends AbstractService{
}
