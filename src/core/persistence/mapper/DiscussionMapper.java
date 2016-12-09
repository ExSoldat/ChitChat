package core.persistence.mapper;

import java.util.ArrayList;

import core.domain.Discussion;

public class DiscussionMapper implements Mapper {

	@Override
	public ArrayList read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Discussion readById(int discussion_id) {
		return MessageMapper.getInstance().readByDiscussionId(discussion_id);
	}

	@Override
	public boolean update(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
