package com.erickgozan.springboot.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.erickgozan.springboot.apirest.models.entity.Usuario;
import com.erickgozan.springboot.apirest.models.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.FindByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("info_adicional", "Hola que tal usuario: ".concat(authentication.getName()));
		
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
	

}
