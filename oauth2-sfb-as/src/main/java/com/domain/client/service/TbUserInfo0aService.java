package com.domain.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domain.client.dao.TbUserInfo0aDao;
import com.domain.client.model.param.TbUserInfo0aGetOneByUsernameParam;
import com.domain.client.model.result.TbUserInfo0aResult;

@Service("tbUserInfo0aService")
public class TbUserInfo0aService implements UserDetailsService
{
	@Autowired
	private TbUserInfo0aDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		TbUserInfo0aGetOneByUsernameParam param = new TbUserInfo0aGetOneByUsernameParam();
		param.setDynamicColumns(new String[] {"tcCode", "tcName", "tcPassword"});
		param.setUsername(username);
		TbUserInfo0aResult tbUser = dao.getOneByUsername(param);
		if (tbUser == null)
		{
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return User.builder().username(tbUser.getTcCode()).password(tbUser.getTcPassword()).roles("ADMIN")
				.build();
	}
}
