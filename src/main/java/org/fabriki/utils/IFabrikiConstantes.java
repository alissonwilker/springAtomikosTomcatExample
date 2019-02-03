package org.fabriki.utils;

import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;

public interface IFabrikiConstantes {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String USER_LOGIN = "userLogin";
    public static final String NOME_INSTITUICAO = "nomeInstituicao";
    public static final String USUARIO_LOGADO_EH_ADMIN = "usuarioLogadoEhAdmin";
    public static final String USUARIO_LOGADO = "usuarioLogado";
    public static final String ID_TURMA = "idTurma";
    public static final String STATE = UUID.randomUUID().toString();
    public static final String REPOSITORY_ACCESS_SCOPE = "public_repo";

    public static final String REPOSITORY_SLUG = System.getenv("REPOSITORY_SLUG");
    public static final String CLIENT_ID = System.getenv("CLIENT_ID");
    public static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    public static final String DEV_ACCESS_TOKEN = System.getenv("dev_" + ACCESS_TOKEN);
    public static final String DEV_USER_LOGIN = System.getenv("dev_" + USER_LOGIN);
    public static final String PROD_ACCESS_TOKEN = System.getenv("PROD_" + ACCESS_TOKEN);
    public static final String PROD_USER_LOGIN = System.getenv("PROD_" + USER_LOGIN);

    public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    public static final String DEFAULT_NOME_INSTITUICAO = "Fabriki";
}