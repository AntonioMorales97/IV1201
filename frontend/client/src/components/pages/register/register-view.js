import React from 'react';
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
            <Alert color='danger'>{t(errorMessageKey)}</Alert>
          ) : null}
          {successMessageKey ? (
            <Alert color='success'>{t(successMessageKey)}</Alert>
          ) : null}
          <Form onSubmit={onSubmit}>
            <FormGroup className='mb-0'>
              <Label for='firstName'>{t('first_name')}</Label>
              <Input
                type='text'
                name='firstName'
                id='firstName'
                placeholder={t('first_name')}
                autoComplete='first name'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='lastName'>{t('last_name')}</Label>
              <Input
                type='text'
                name='lastName'
                id='lastName'
                placeholder={t('last_name')}
                autoComplete='last name'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='ssn'>{t('ssn')}</Label>
              <Input
                type='text'
                name='ssn'
                id='ssn'
                placeholder={t('ssn')}
                autoComplete='last name'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='username'>{t('username')}</Label>
              <Input
                type='text'
                name='username'
                id='username'
                placeholder={t('username')}
                autoComplete='username'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='email'>{t('email')}</Label>
              <Input
                type='email'
                name='email'
                id='email'
                placeholder={t('email')}
                autoComplete='username email'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='password'>{t('password')}</Label>
              <Input
                type='password'
                name='password'
                id='password'
                placeholder={t('password')}
                autoComplete='current-password'
                className='mb-3'
                onChange={onChange}
              />
              <Button color='dark' style={{ marginTop: '2rem' }} block>
                {tryRegister ? <Spinner size='sm' /> : t('register')}
              </Button>
              <div className='text-center mt-3'>
                {t('already_have_account?')}{' '}
                <Link to='/login'>{t('login')}</Link>
              </div>
            </FormGroup>
          </Form>
        </Container>
      </Container>
    </div>
  );
};

RegisterView.propTypes = {};

export default RegisterView;
