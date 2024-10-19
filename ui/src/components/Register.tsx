import {
    Avatar,
    Box,
    Button,
    Container,
    CssBaseline,
    Grid,
    TextField,
    Typography,
  } from "@mui/material";
import { LockOutlined } from "@mui/icons-material";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN_NAME } from "../constants/apiConstants";

  const Register = () => {
    const navigate = useNavigate()
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
  
    const handleRegister = async () => {
        if (password.length && name.length) {
            const body = {
                "username": name,
                "password": password
            }

            axios.post(API_BASE_URL + '/user/register', body).then(
                function (response) {
                    if(response.status === 200) {
                        localStorage.setItem(ACCESS_TOKEN_NAME, response.data.token);
                        navigate('/login')
                    } else {
                        console.log("Some error ocurred");
                    }
                }
            );
        }
    };
  
    return (
      <>
        <Container maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              mt: 20,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "primary.light" }}>
              <LockOutlined />
            </Avatar>
            <Typography variant="h5">Criar usuário</Typography>
            <Box sx={{ mt: 3 }}>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    name="name"
                    required
                    fullWidth
                    id="name"
                    label="Nome de usuário"
                    autoFocus
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </Grid>
  
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="password"
                    label="Senha"
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </Grid>
              </Grid>
              <Button
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                onClick={handleRegister}
              >
                Registrar
              </Button>
              <Grid container justifyContent="flex-end">
                <Grid item>
                  <Link to="/login">Já possui uma conta? Faça o login</Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </>
    );
  };
  
  export default Register;