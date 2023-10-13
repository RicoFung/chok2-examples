package com.domain.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domain.client.dao.TbUserInfo0aDao;
import com.domain.client.model.entity.TbUserInfo0aEntity;
import com.domain.client.model.query.TbUserInfo0aGetOneByUsernameQuery;

@Service("tbUserInfo0aService")
public class TbUserInfo0aService implements UserDetailsService
{
	@Autowired
	private TbUserInfo0aDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		TbUserInfo0aGetOneByUsernameQuery query = new TbUserInfo0aGetOneByUsernameQuery();
		query.setDynamicColumns(new String[] {"tcCode", "tcName", "tcPassword"});
		query.setUsername(username);
		TbUserInfo0aEntity tbUser = dao.getOneByUsername(query);
		if (tbUser == null)
		{
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return User.builder().username(tbUser.getTcCode()).password(tbUser.getTcPassword()).roles("ADMIN")
				.build();
	}
}
