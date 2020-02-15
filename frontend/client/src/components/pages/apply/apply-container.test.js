import React from 'react';
import { mount, shallow } from 'enzyme';
import Apply from './apply-container';

describe('running apply-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(<Apply />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration render without crashing', () => {
    const wrapper = mount(<Apply />);
    expect(wrapper.exists()).toBe(true);
  });
});
