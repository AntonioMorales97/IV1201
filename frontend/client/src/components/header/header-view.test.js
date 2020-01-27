import React from 'react';
import { shallow } from 'enzyme';
import HeaderView from './header-view';

const mockAuth = {
  isAuthenticated: false
};

describe('running header-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(
      <HeaderView
        t={k => k}
        languageCodeToName={jest.fn()}
        toggle={jest.fn()}
        currentLanguage='English'
        dropDownItems={[]}
        auth={mockAuth}
      />
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(wrapper.containsMatchingElement(<h4>brand</h4>)).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
