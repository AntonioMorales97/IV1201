import React from 'react';
import { shallow } from 'enzyme';
import ApplicationItem from './application-item';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    return useMock;
  }
}));

const application = {
  id: 1,
  firstName: 'firstName',
  lastName: 'lastName',
  ssn: '1231231231',
  status: {
    name: 'accepted'
  }
};

describe('running application-item test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(<ApplicationItem application={application} />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(
      wrapper.containsMatchingElement(
        <div className='col-12 col-md-3'>
          {application.firstName + ' ' + application.lastName}
        </div>
      )
    ).toEqual(true);
    expect(
      wrapper.containsMatchingElement(
        <div className='col-12 col-md-3'>{application.ssn}</div>
      )
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
