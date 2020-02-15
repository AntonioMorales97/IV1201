import React from 'react';
import { shallow } from 'enzyme';
import ApplyView from './apply-view';

describe('running apply-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(<ApplyView />);
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(wrapper).toMatchSnapshot();
  });
});
