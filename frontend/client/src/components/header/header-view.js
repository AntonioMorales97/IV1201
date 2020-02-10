import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavLink,
  NavItem,
  Container,
  DropdownMenu,
  DropdownToggle,
  UncontrolledButtonDropdown
} from 'reactstrap';
import { Link } from 'react-router-dom';

const HeaderView = props => {
  const {
    isOpen,
    t,
    toggle,
    currentLanguage,
    languageCodeToName,
    dropDownItems,
    auth,
    logout
  } = props;

  const guestLinks = (
    <Fragment>
      <NavItem>
        <NavLink tag={Link} to='/' onClick={toggle}>
          {t('home')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/register' onClick={toggle}>
          {t('register')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/login' onClick={toggle}>
          {t('login')}
        </NavLink>
      </NavItem>
    </Fragment>
  );

  const recruitLinks = (
    <Fragment>
      <NavItem>
        <NavLink tag={Link} to='/' onClick={toggle}>
          {t('home')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/applications' onClick={toggle}>
          {t('application.applications')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/login' onClick={logout}>
          {t('logout')}
        </NavLink>
      </NavItem>
    </Fragment>
  );

  const applicantLinks = (
    <Fragment>
      <NavItem>
        <NavLink tag={Link} to='/' onClick={toggle}>
          {t('home')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/apply' onClick={toggle}>
          {t('apply')}
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink tag={Link} to='/login' onClick={logout}>
          {t('logout')}
        </NavLink>
      </NavItem>
    </Fragment>
  );

  const languageSelector = (
    <UncontrolledButtonDropdown>
      <DropdownToggle color='light'>
        {languageCodeToName(currentLanguage)}
      </DropdownToggle>
      <DropdownMenu>{dropDownItems}</DropdownMenu>
    </UncontrolledButtonDropdown>
  );

  const links = () => {
    if (auth.isAuthenticated) {
      if (auth.user.role === 'RECRUIT') {
        return recruitLinks;
      } else {
        return applicantLinks;
      }
    } else {
      return guestLinks;
    }
  };

  return (
    <Navbar color='light' light expand='sm' className='mb-3 fixed-top'>
      <Container>
        <NavbarBrand>
          <h4>{t('brand')}</h4>
        </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className='ml-auto' navbar>
            {links()}
            {languageSelector}
          </Nav>
        </Collapse>
      </Container>
    </Navbar>
  );
};

HeaderView.propTypes = {
  isOpen: PropTypes.bool,
  toggle: PropTypes.func.isRequired,
  t: PropTypes.func.isRequired,
  currentLanguage: PropTypes.string.isRequired,
  languageCodeToName: PropTypes.func.isRequired,
  dropDownItems: PropTypes.array.isRequired
};

export default HeaderView;
