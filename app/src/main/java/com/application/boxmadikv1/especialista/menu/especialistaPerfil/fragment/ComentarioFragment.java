package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment;

public class ComentarioFragment {
        /*extends BaseFragment<ComentarioView, ComentarioPresenter> implements ComentarioView {

    public static final String TAG = ComentarioFragment.class.getSimpleName();

    @BindView(R.id.textViewTituloPropuesta)
    TextView textViewTituloPropuesta;
    @BindView(R.id.textViewFecha)
    TextView TextViewFecha;
    @BindView(R.id.edtContentDetalle)
    TextInputEditText textInputEditTextDetallePropuesta;
    @BindView(R.id.textViewNombre2)
    TextView textViewNombreCliente;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBarCliente;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextComentario;
    @BindView(R.id.circleImage)
    CircleImageView circleImageViewPerfilCliente;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.content_container)
    TableView table;
    private ComentarioListener comentarioListener;
    private CursosAdapter setAdapter;


    public static ComentarioFragment newInstance(ComentariosUi comentariosUi) {
        Bundle bundle = new Bundle();
        ComentarioFragment fragment = new ComentarioFragment();
        bundle.putParcelable("coment", comentariosUi);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ComentarioPresenter getPresenter() {
        ComentarioRepository comentarioRepository = ComentarioRepository.getmInstance(new ComentarioRemote());
        return new ComentarioPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListaCursos(comentarioRepository));
    }

    @Override
    protected ComentarioView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_comentario_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        setAdapter = new CursosAdapter(getActivity());
        table.setAdapter(setAdapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDataInicial(ComentariosUi comentariosUi) {
        Log.d(TAG, "mostrar Data Inicial " + comentariosUi.getNombreCliente());
        textViewTituloPropuesta.setText(comentariosUi.getNombrePropuesta());
        TextViewFecha.setText("Fecha : " + comentariosUi.getFechaPropuesta());
        textInputEditTextDetallePropuesta.setText(comentariosUi.getDetallePropuesta());
        textViewNombreCliente.setText(comentariosUi.getNombreCliente());
        float porcentaje = Float.parseFloat(comentariosUi.getEstrellasEspec());
        ratingBarCliente.setRating(porcentaje);
        textInputEditTextComentario.setText(comentariosUi.getComentarioEspec());
        Glide.with(getActivity()).load(comentariosUi.getFotoCliente()).into(circleImageViewPerfilCliente);
        Glide.with(getActivity()).load(comentariosUi.getImagenRubro()).into(imageViewRubro);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaTablas(List<ColumnaCabeceraCursos> columnHeaderList, List<FilaCabeceraCursos> rowHeaderList, List<List<CeldasCursos>> cellsList) {
        setAdapter.setAllItems(columnHeaderList, rowHeaderList, cellsList);
    }

    @Override
    public void initListenerCursos(String keyUser) {
        if(comentarioListener!=null)comentarioListener.onClickCursosListener(keyUser);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            comentarioListener = (ComentarioListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @OnClick({R.id.textViewCursos})
    public void onClick(View view){
        int itemId = view.getId();
        switch (itemId){
            case R.id.textViewCursos:
                presenter.onClickCursos();
                break;
        }
    }*/
}
