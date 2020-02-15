import React from 'react';
import { shallow } from 'enzyme';
import ApplicationsView from './applications-view';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    return useMock;
  }
}));

describe('running applications-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(<ApplicationsView applications={[]} />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(
      wrapper.containsMatchingElement(<h4>application.no_applications</h4>)
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
