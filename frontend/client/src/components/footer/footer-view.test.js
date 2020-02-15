import React from 'react';
import { shallow } from 'enzyme';
import FooterView from './footer-view';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    useMock.i18n = {};
    return useMock;
  }
}));

describe('running footer-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(<FooterView />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(wrapper.containsMatchingElement(<h5>brand</h5>)).toEqual(true);
    expect(
      wrapper.containsMatchingElement(<p>copyright &copy; 2020 </p>)
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
