import React from 'react';
import { shallow } from 'enzyme';
import { ListGroupItem } from 'reactstrap';
import ApplicationView from './application-view';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    return useMock;
  }
}));

const person = {
  firstName: 'firstName',
  lastName: 'lastName',
  email: 'email',
  ssn: '1231231231'
};

const application = {
  id: 1,
  status: {
    name: 'accepted'
  },
  person,
  competenceProfile: [],
  availability: []
};

describe('running application-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(
      <ApplicationView
        application={application}
        acceptApplication={jest.fn()}
        rejectApplication={jest.fn()}
        unhandleApplication={jest.fn()}
      />
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(
      wrapper.containsMatchingElement(
        <span className='back-text'>application.back_to_applications</span>
      )
    ).toEqual(true);
    expect(
      wrapper.containsMatchingElement(<h4>application.no_experiences</h4>)
    ).toEqual(true);
    expect(
      wrapper.containsMatchingElement(<h4>application.no_availability</h4>)
    ).toEqual(true);
    expect(
      wrapper.containsMatchingElement(
        <ListGroupItem>
          <span className='bold'>personal.ssn: </span>
          {person.ssn}
        </ListGroupItem>
      )
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
