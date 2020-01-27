import React from 'react';
import { shallow } from 'enzyme';
import LoginView from './login-view';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    return useMock;
  }
}));

describe('running login-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(
      <LoginView
        renderRedirect={jest.fn()}
        onSubmit={jest.fn()}
        onChange={jest.fn()}
      />
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(
      wrapper.containsMatchingElement(
        <div className='h3 text-center'>login</div>
      )
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
