import React from 'react';
import { mount, shallow } from 'enzyme';
import Footer from './footer-container';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    useMock.i18n = {};
    return useMock;
  }
}));

describe('running footer-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(<Footer />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration render without crashing', () => {
    const wrapper = mount(<Footer />);
    expect(wrapper.exists()).toBe(true);
  });
});
