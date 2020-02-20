import React from 'react';
import PropTypes from 'prop-types';
import {
  Container,
  Button,
  Form,
  FormGroup,
  Label,
  Input,
  Alert,
  Spinner
} from 'reactstrap';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';

import './register.css';

/**
 * The view of the register page.
 */
const RegisterView = props => {
  const { t } = useTranslation();
  const {
    onSubmit,
    onChange,
    tryRegister,
    errorMessageKey,
    successMessageKey,
    renderRedirect
  } = props;
  return (
    <div className='register'>
      {renderRedirect()}
      <Container>
        <Container className='form-container'>
          <div className='h3 text-center'>{t('register')}</div>
          {errorMessageKey ? (
            <Alert color='danger'>{t('error.' + errorMessageKey)}</Alert>
          ) : null}
          {successMessageKey ? (
            <Alert color='success'>{t('sucess.' + successMessageKey)}</Alert>
          ) : null}
          <Form onSubmit={onSubmit}>
            <FormGroup className='mb-0'>
              <Label for='firstName'>{t('personal.first_name')}</Label>
              <Input
                type='text'
                name='firstName'
                id='firstName'
                placeholder={t('personal.first_name')}
                autoComplete='first name'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='lastName'>{t('personal.last_name')}</Label>
              <Input
                type='text'
                name='lastName'
                id='lastName'
                placeholder={t('personal.last_name')}
                autoComplete='last name'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='ssn'>{t('personal.ssn')}</Label>
              <Input
                type='text'
                name='ssn'
                id='ssn'
                placeholder={t('personal.ssn')}
                autoComplete='ssn'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='username'>{t('personal.username')}</Label>
              <Input
                type='text'
                name='username'
                id='username'
                placeholder={t('personal.username')}
                autoComplete='username'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='email'>{t('personal.email')}</Label>
              <Input
                type='email'
                name='email'
                id='email'
                placeholder={t('personal.email')}
                autoComplete='username email'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='password'>{t('personal.password')}</Label>
              <Input
                type='password'
                name='password'
                id='password'
                placeholder={t('personal.password')}
                autoComplete='current-password'
                className='mb-3'
                onChange={onChange}
              />
              <Button color='dark' style={{ marginTop: '2rem' }} block>
                {tryRegister ? <Spinner size='sm' /> : t('register')}
              </Button>
              <div className='text-center mt-3'>
                {t('form.already_have_account?')}{' '}
                <Link to='/login'>{t('login')}</Link>
              </div>
            </FormGroup>
          </Form>
        </Container>
      </Container>
    </div>
  );
};

RegisterView.propTypes = {
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  tryRegister: PropTypes.bool.isRequired,
  errorMessageKey: PropTypes.number,
  successMessageKey: PropTypes.number,
  renderRedirect: PropTypes.func.isRequired
};

export default RegisterView;
