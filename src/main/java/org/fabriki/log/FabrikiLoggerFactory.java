package org.fabriki.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária que tem por objetivo fabricar logger's que possuem um prefixo em cada mensagem
 * de log. Sugestão: usar em classes cuja responsabilidade é se comunicar com um sistema externo, de
 * forma a deixar bem explícito nos logs que naquele momento está sendo realizada uma integração com
 * um sistema externo. Motivação: acessos recorrentes a sistemas externos podem penalizar o
 * desempenho da aplicação.
 *
 */
public class FabrikiLoggerFactory {
    public static Logger getLogger(Class<?> clazz, String logTagPrefix) {
        return LoggerFactory.getLogger(clazz + logTagPrefix);
    }
}
